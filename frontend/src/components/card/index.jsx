import styles from "./index.module.scss";
import { Price } from "../price";
import {HeartIcon} from "./cardPart/HeartIcon/index.jsx";
import {ProductTitle} from "./cardPart/CardTitle/index.jsx";
import {CardImage} from "./cardPart/CardImage/index.jsx";
import {useGetImageByIdQuery} from "../../api/product.js";

export const Card = ({id, imageUrl, productName, onClick, price, discount}) => {

  const handleHeartClick = (e) => {
    e.stopPropagation();
  };
  const {data, isSuccess, error} = useGetImageByIdQuery(id)

  return (
    <div onClick={onClick} className={styles.root}>
      <div className={styles.container}>
        <div className={styles.card}>
          <HeartIcon onClick={handleHeartClick} />
          <CardImage
              data={data}
              imageUrl={imageUrl}
          />
          <ProductTitle productName={productName} />
          <Price
            discountPercentage={discount.discount_percent}
            originalPrice={price * 3}
            discountedPrice={price}
          />
        </div>
      </div>
    </div>
  );
};
