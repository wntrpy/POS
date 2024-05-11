package application;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.image.Image;

public class DBImageHandler {
    private static String errorMessage;

    public static String getItemImageHandlerErrorMessage() {
        return errorMessage;
    }

    public static void setItemPicture(int itemID,String filePath) {
        updateDatabaseImage(itemID, null, filePath, "UPDATE ITEM SET ItemPicture = ? WHERE ItemID = ?");
    }

    public static void setUserPhoto(String userID, String filePath) {
        updateDatabaseImage(-10, userID, filePath, "UPDATE SYSUSER SET UserPhoto = ? WHERE UserID = ?");
    }

    public static Image getItemImage(int itemID) {
        return byteArrayToImage(fetchItemImageFromDatabase(itemID,null,"SELECT ItemPicture FROM Retail_Inventory_ALL WHERE ItemID = ?","ItemPicture"));
    }

    public static Image getUserPhoto(String UserID) {
        return byteArrayToImage(fetchItemImageFromDatabase(-10,UserID,"SELECT UserPhoto FROM SYSUSER WHERE UserID = ?","UserPhoto"));
    }


    private static void updateDatabaseImage(int itemID, String userID, String filePath, String query) {

        try {
            // Prepare the statement with parameters
            PreparedStatement pstmt = NXTVMain.local.getConnection().prepareStatement(query);

            // Set the image data
            File imageFile = new File(filePath);
            FileInputStream fis = new FileInputStream(imageFile);
            pstmt.setBinaryStream(1, fis, (int) imageFile.length()); // Set the photo data

            // Set the ID if userID or itemID
            if (userID == null) {
                pstmt.setInt(2, itemID);
            }
            else {
                pstmt.setString(2, userID);
            }

            // Execute the update
            pstmt.executeUpdate();

            System.out.println("Photo updated successfully.");

        }
        catch (Exception e) {
            errorMessage = "Error Uploading photo to database.";
            System.out.println(errorMessage);
            e.printStackTrace();
        }

    }

    /**
     * Fetches item image bytes from the database
     *
     * @param itemID of the item
     * @return byte [] of image, null if null.
     */

    private static byte[] fetchItemImageFromDatabase(int itemID, String UserID, String query, String column) {
        byte[] imageData = null;

        try (PreparedStatement ps = NXTVMain.local.getConnection().prepareStatement(query)) {

            if (UserID == null) { // if null edi item yung gineget
                ps.setInt(1, itemID);
            }
            else {
                ps.setString(1, UserID);
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                imageData = rs.getBytes(column); // Get image data from the result set
            }

        }
        catch (SQLException e) {
            errorMessage = "Failed to fetch Photo From Database.";
            System.out.println(errorMessage);
            e.printStackTrace();
        }
        return imageData;
    }

    /**
     * Converts a byte array into a JavaFX Image.
     *
     * @param imageBytes The byte array containing the image data.
     * @return A JavaFX Image, or null if the input data is invalid.
     */
    private static Image byteArrayToImage(byte[] imageBytes) {
        if (imageBytes == null || imageBytes.length == 0) {
            return null; // Return null if the byte array is empty or null
        }

        try {
            // Create an InputStream from the byte array
            ByteArrayInputStream byteStream = new ByteArrayInputStream(imageBytes);

            // Create a JavaFX Image from the InputStream
            return new Image(byteStream);

        }
        catch (Exception e) {
            // Handle exceptions, e.g., if the byte array doesn't represent a valid image
            errorMessage ="byte array invalid and cannot produce an image.";
            System.out.println(errorMessage);
            e.printStackTrace();
            return null; // Return null in case of an error
        }
    }
}
