import styles from "./index.module.scss";
import {CardImage} from "../../card/cardPart/CardImage";

export const ProductImage = () => {
  return (
    <div className={styles.root}>
      <div className={styles.image}>
          <img src="/assets/product.png" />
      </div>
      <div className={styles.images}>
        <img src="/assets/product.png" />
        <img src="/assets/product.png" />
        <img src="/assets/product.png" />
      </div>
    </div>
  );
};
