package com.TRA.tra24Springboot.Controllers;
import com.TRA.tra24Springboot.Logging.TrackExecutionTime;
import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class ReportingController {


    Inventory globalInventoryItem;

    //reporting all Inventory
    @GetMapping("report")
    public String reportInventory() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("************* Report *************\n");
        //sorting products by name
        List<Product> sortedProducts = globalInventoryItem.getProducts().stream().collect(Collectors.toList());

        //parsing and display sorted product information
        String productsInfo = parseProductsInfo(sortedProducts);
        stringBuilder.append("Sorted Products:\n").append(productsInfo);
        stringBuilder.append("Location: " + globalInventoryItem.getLocation() + "\n");
        stringBuilder.append("Manager: " + globalInventoryItem.getAdmin() + "\n");
        stringBuilder.append("Supplier: " + globalInventoryItem.getSupplier() + "\n");
        stringBuilder.append("PhoneNumber :" + globalInventoryItem.getPhoneNumber() + "\n");
        stringBuilder.append("OpeningHours: " + globalInventoryItem.getOpeningHours() + "\n");
        stringBuilder.append("ClosingHours: " + globalInventoryItem.getClosingHours() + "\n");

        return stringBuilder.toString();
    }

    private String parseProductsInfo(List<Product> products) {
        StringBuilder productsInfo = new StringBuilder();
        for (Product product : products) {
            productsInfo.append("Name: ").append(product.getProductDetails().getName() + "\n");
            productsInfo.append("Country of Origin: ").append(product.getProductDetails().getCountryOfOrigin() + "\n");
            productsInfo.append("Description : ").append(product.getProductDetails().getDescription() + "\n");
            productsInfo.append("Quantity  : ").append(product.getQuantity() + "\n");
            productsInfo.append("category : ").append(product.getCategory() + "\n");
            productsInfo.append("Color  : ").append(product.getProductDetails().getColor() + "\n");
            productsInfo.append("Size : ").append(product.getProductDetails().getSize() + "\n");
            productsInfo.append("sku  : ").append(product.getSku() + "\n");
            productsInfo.append(" price : ").append(product.getProductDetails().getPrice() + "\n");

            //add other product attributes as needed
            productsInfo.append("\n");
        }
        return productsInfo.toString();
    }
}
