## Ideas : 

- [ ] Refactor to cqrs (store orders in redis with ttl 1 week)
- [ ] Add orders-history service (store in postgres orders, received from rabbitmq queue)
- [ ] (?) Add storage service (to keep quantity of products, receive new items, update it)
- [ ] Add minio to store product images
- [ ] Add auth service (with auth table to store sessions(with auth table to store sessions)
- [ ] Add user service  

---

### Notes :

- > Consumer is listening for changes in domain from producer of events, stores it in redis.  
  > Redis is responsible for storing data about orders for 1 week  
  > Order-history service is listening for creation, updates of order domain, stores it in db
  
- > Storage service provides an api of managing different storage centers, quantity of products (?) grouping by types  
  > Printing smth like [ricevutas](https://www.soldioggi.it/ricevuta-di-pagamento-16164.html) about shipping and receiving products 
  > 
