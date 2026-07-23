# POS

A JavaFX-based Point of Sale (POS) desktop application for managing store operations, browsing inventory, and building a checkout cart.

## Overview

This project appears to be a retail POS system with a dark-themed JavaFX interface. It includes a main dashboard, inventory browsing, cart management, item details, user profile display, and database-backed image handling.

## Features

- JavaFX desktop UI
- Inventory/item table with product details
- Add-to-cart flow from item cards and table actions
- Checkout screen and cart table view
- User dashboard and main navigation frame
- Database image upload/retrieval for item photos and user photos
- Custom styling with JavaFX CSS

## Project Structure

- `application/` - shared application classes and helpers
- `Controllers/` - JavaFX controllers for the main UI and POS screens
- `FXML/` - JavaFX layout files
- `Styles/` - CSS styling for the interface

## Requirements

- Java 17 or later
- JavaFX SDK
- Database connection configured in the application

## Getting Started

1. Clone the repository.
2. Open the project in your preferred Java IDE.
3. Configure the JavaFX module path and required modules.
4. Set up the database connection used by the application.
5. Run the main entry point of the app.

Example JVM arguments:

```bash
--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml,javafx.graphics
```

## Notes

- The project uses JavaFX `TableView` components for inventory and cart views.
- Item images and user photos are loaded from the database.
- The app currently includes work-in-progress comments in the controllers around cart quantity handling, delete actions, and checkout flow.

## Troubleshooting

If you run into Java memory issues, try lowering the heap size or closing other applications before launching the app.

## License

No license file was found in the repository.
