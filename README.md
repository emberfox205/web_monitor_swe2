# Website Monitoring System

A lightweight, Java-based client-server application that allows users to subscribe to website updates via the command line. The server periodically checks monitored URLs and dispatches mock notifications directly to the connected client terminal.

## Features

* **Client-Server Architecture:** Operates over standard Java Sockets.
* **Custom Subscriptions:** Users can specify the target URL, notification channel (e.g., EMAIL, SMS), and polling frequency.
* **Real-time Alerts:** The server automatically pushes notification updates to the active client when changes are "detected."

## Getting Started

### Prerequisites

* Java Development Kit (JDK) 8 or higher.

### Running the Application

1. **Compile the code:** Navigate to the root folder (the folder containing the `src` directory) and compile the `.java` files.
2. **Start the Server:** Open your first terminal and execute:
```bash
java com.monitor.server.ServerApp

```


3. **Start the Client:** Open a second terminal and execute:
```bash
java com.monitor.client.ClientApp

```



## Usage & Commands

Once the client connects to the server, you can use the following commands in the client terminal:

* **`ADD <url> <channel> <frequency>`**
  Creates a new subscription.
  *(Example: `ADD google.com EMAIL DEMO_10_SEC`)*
* **`LIST`**
  Displays all of your currently active subscriptions and their IDs.
* **`CANCEL <subscriptionId>`**
  Cancels an active subscription using the 8-character ID provided upon creation.
* **`EXIT`**
  Disconnects the client and closes the application.