import React, { useEffect, useState } from "react";
import {
  Box,
  Typography,
  Paper,
  List,
  ListItem,
  ListItemText,
} from "@mui/material";

const LogViewer = () => {
  const [logs, setLogs] = useState([]);

  useEffect(() => {
    // Establish WebSocket connection
    const socket = new WebSocket("ws://localhost:8080/logs");

    // Handle incoming messages
    socket.onmessage = (event) => {
      setLogs((prevLogs) => [...prevLogs, event.data]);
    };

    // Handle WebSocket connection events
    socket.onopen = () => console.log("WebSocket connection established.");
    socket.onclose = () => console.log("WebSocket connection closed.");
    socket.onerror = (error) => console.error("WebSocket error:", error);

    // Clean up the WebSocket connection
    return () => socket.close();
  }, []);

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        height: "100vh",
        backgroundColor: "#f5f5f5",
      }}
    >
      <Typography variant="h4" gutterBottom>
        Real-Time Log Viewer
      </Typography>
      <Paper
        elevation={3}
        sx={{
          width: "80%",
          maxHeight: "70vh",
          overflowY: "auto",
          padding: "16px",
          backgroundColor: "#ffffff",
        }}
      >
        <List>
          {logs.map((log, index) => (
            <ListItem key={index}>
              <ListItemText primary={log} />
            </ListItem>
          ))}
        </List>
      </Paper>
    </Box>
  );
};

export default LogViewer;
