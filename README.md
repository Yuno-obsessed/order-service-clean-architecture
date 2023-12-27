# Order-Service  

- [x] CQRS separation of orders:
- Command/query handlers for IPC, 
- Storing query responsibility in Redis
- Storing command responsibility in Postgres
- Synchronizing both responsibilities using RabbitMQ and domain events

- [x] Clean architecture:
- Principles of SOLID
- RSP (Reuse/Release Equivalence Principles) 
- Precise division of layers:
> Domain - layer that serves as implementation of critical business rules, is a core and has no dependencies on anything except of itself;  
> 
> Application - layer that serves as implementation of application business rules, 
> invokes critical (domain) business rules and defines interfaces to implement in infrastructure layer;  
>  
> Infrastructure - layer of driven (secondary) adapters, which wrap around a tool (db, message bus, etc.) 
> and adapt it's I/O to a port, which fits the Application layer needs;  
> 
> Presentation - layer of driving (primary) adapters, which wrap around a use case and adapt it's I/O to a delivery 
> mechanism (HTTP/HTML, HTTP/JSON, gRPC/Protocol Buffers, etc.).