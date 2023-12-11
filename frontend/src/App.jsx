import styles from "./global.module.scss";
import { Header } from "./components/header/index.jsx";
import { MainPage } from "./pages/main";
import { Route, Routes } from "react-router-dom";
import { ProductPage } from "./pages/product";

function App() {
  return (
    <>
      <Header />
      <div className={styles.root}>
        <Routes>
          <Route index element={<MainPage />} />
          <Route path="/product/:cardId" element={<ProductPage />} />
        </Routes>
      </div>
    </>
  );
}

export default App;
