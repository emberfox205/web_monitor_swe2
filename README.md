# Website Monitoring System

A Java-based client-server application that allows users to subscribe to website updates via the command line. The server periodically checks monitored URLs and dispatches mock notifications directly to the connected client terminal.

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
* **`CANCEL <subscriptionId>`**
  Cancels an active subscription using the ID provided upon creation.