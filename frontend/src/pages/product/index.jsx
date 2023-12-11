import styles from "./index.module.scss";
import { ProductImage } from "../../components/product/image";
import { Route } from "../../components/route /index.jsx";
import { ProductDetails } from "../../components/product/details";
import { ProductDelivery } from "../../components/product/delivery";

export const ProductPage = () => {
  return (
    <div>
      <Route />
      <div className={styles.root}>
        <ProductImage />
        <div className={styles.detailsContainer}>
          <ProductDetails />
          <ProductDelivery />
        </div>
      </div>
    </div>
  );
};
