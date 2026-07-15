package com.example.MyWebsite.service.InventoryService;

import com.example.MyWebsite.entites.InventoryEntity.Product;
import com.example.MyWebsite.entites.InventoryEntity.Stock;
import com.example.MyWebsite.entites.InventoryEntity.Warehouse;
import com.example.MyWebsite.repository.InventoryRepository.ProductRepository;
import com.example.MyWebsite.repository.InventoryRepository.StockRepository;
import com.example.MyWebsite.repository.InventoryRepository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class InventoryService {

    @Autowired private ProductRepository productRepository;
    @Autowired private WarehouseRepository warehouseRepository;
    @Autowired private StockRepository stockRepository;

    // --- Product CRUD ---
    public Product saveProduct(Product product) { return productRepository.save(product); }
    public List<Product> getAllProducts() { return productRepository.findAll(); }
    public void deleteProduct(Long id) { productRepository.deleteById(id); }

    // --- Warehouse Logic ---
    public Warehouse saveWarehouse(Warehouse warehouse) { return warehouseRepository.save(warehouse); }
    public List<Warehouse> getAllWarehouses() { return warehouseRepository.findAll(); }

    // --- Stock Update Logic ---
    public Stock updateStock(Long productId, Long warehouseId, Integer quantity) {
        Product prod = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product nahi mila"));
        Warehouse wh = warehouseRepository.findById(warehouseId).orElseThrow(() -> new RuntimeException("Warehouse nahi mila"));

        Stock stock = stockRepository.findByProductIdAndWarehouseId(productId, warehouseId)
                .orElse(new Stock());

        stock.setProduct(prod);
        stock.setWarehouse(wh);
        stock.setQuantity(quantity);
        return stockRepository.save(stock);
    }

    public List<Stock> getAllStock() { return stockRepository.findAll(); }
    public List<Stock> getLowStockAlerts() { return stockRepository.findLowStockItems(); }

    // --- Inter-Warehouse Stock Transfer ---
    @Transactional
    public void transferStock(Long productId, Long fromWhId, Long toWhId, Integer qty) {
        Stock sourceStock = stockRepository.findByProductIdAndWarehouseId(productId, fromWhId)
                .orElseThrow(() -> new RuntimeException("Source warehouse me product ka stock nahi mila!"));

        if (sourceStock.getQuantity() < qty) {
            throw new RuntimeException("Transfer ke liye source warehouse me itna stock nahi hai!");
        }

        // Deduct from Source
        sourceStock.setQuantity(sourceStock.getQuantity() - qty);
        stockRepository.save(sourceStock);

        // Add to Destination
        Stock destStock = stockRepository.findByProductIdAndWarehouseId(productId, toWhId)
                .orElse(new Stock());

        if(destStock.getId() == null) {
            Product prod = productRepository.findById(productId).orElseThrow();
            Warehouse wh = warehouseRepository.findById(toWhId).orElseThrow();
            destStock.setProduct(prod);
            destStock.setWarehouse(wh);
            destStock.setQuantity(0);
        }

        destStock.setQuantity(destStock.getQuantity() + qty);
        stockRepository.save(destStock);
    }
}