# UDPTelemetry
Detailed README-style Description:
This project is a Java-based UDP receiver application. It listens for 1000 messages from a corresponding sender and calculates the latency between sending and receiving each message in both nanoseconds and milliseconds.

Features:

Receives data over UDP

Calculates per-message latency (ns and ms)

Uses ByteBuffer to extract send time from message

Prints latency results to the console

This repository can be used together with a matching sender program to test and measure UDP message transmission delays.
