import styles from "./global.module.scss";
import { Header } from "./components/header/index.jsx";
import { MainPage } from "./pages/main";
import { Route, Routes } from "react-router-dom";
import { ProductPage } from "./pages/product";
import { LoginPage } from "./pages/login/index.jsx";
import {Footer} from "./components/footer";
import {PrivateRoute} from "./components/privateRoute";

function App() {

  return (
    <>
      <Header />
      <div className={styles.root}>
        <Routes>
          <Route index element={<MainPage />} />
          <Route path="/product/:cardId" element={<PrivateRoute children={<ProductPage/>}/>} />
          <Route path="/login" element={<LoginPage />} />
        </Routes>
      </div>
        <Footer/>
    </>
  );
}

export default App;
