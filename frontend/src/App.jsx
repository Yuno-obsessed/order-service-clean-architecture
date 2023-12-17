import styles from "./global.module.scss";
import { Header } from "./components/header/index.jsx";
import { MainPage } from "./pages/main";
import { Route, Routes } from "react-router-dom";
import { ProductPage } from "./pages/product";
import { LoginPage } from "./pages/login/index.jsx";
import {Footer} from "./components/footer";
import {PrivateRoute} from "./components/privateRoute";
import {CartPage} from "./pages/cart";
import {WishPage} from "./pages/wishlist/index.jsx";
import {useEffect} from "react";

function App() {
    useEffect(() => {
        const data = fetch('http://localhost:8080/api/v1/product', {
            body: JSON.stringify({
            "description": "dsadsasdadefkqlwdnq",
            "name": "Asus dsa F15 A3",
            "subtype_id": 1,
            "price": 14200,
            "discount_id": "8de076c9-b617-48e6-91e5-7d5b84fe0764",
            "quantity": 2
            }),
            method: "POST",
            headers: {
                'Authorization': localStorage.getItem('token')
            }
        })
            .then(res => res.json)
        return data
    })
  return (
    <>

      <Header />
      <div className={styles.root}>
        <Routes>
          <Route index element={<MainPage />} />
          <Route path="/product/:cardId" element={<ProductPage/>} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/cart" element={<CartPage />} />
          <Route path="/wishlist" element={<WishPage />} />
        </Routes>
      </div>
        <Footer/>
    </>
  );
}

export default App;
