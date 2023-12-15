import styles from "./index.module.scss";
import { Button } from "../../button";
import { Price } from "../../price";
import { IconCard } from "../../icon";
import { heart } from "../../../../public/assets/icons/svg.jsx";
import {Avalible} from "../../avalible";

export const ProductDetails = ({price, discount, name, avalible}) => {

  return (
    <div className={styles.root}>
      <div className={styles.details}>
        <h1>{name}</h1>
        <Avalible
            availability={avalible}
        />
      </div>
      <span>
        <Price
            originalPrice = {price}
          discountPercentage = {discount.discount_percent}
  discountedPrice = {price * (discount.discount_percent / 100)}
  button = {false}
        />
        <div className={styles.buttons}>
          <Button
            variation={"original"}
            img={"/assets/cart.svg"}
            text={"Придбати"}
          />
          <IconCard ico={heart("40px", "40px")} />
        </div>
      </span>
    </div>
  );
};
