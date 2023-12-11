import styles from "./index.module.scss";
import { Button } from "../../button";
import { Price } from "../../price";
import { IconCard } from "../../icon";
import { heart } from "../../../../public/assets/icons/svg.jsx";

export const ProductDetails = () => {
  const cards = [
    {
      productId: "31109",
      productName: "LEGO Icons Władca Pierścieni: Rivendell",
      price: {
        originalPrice: "2089.00",
        discountedPrice: "1849.00",
        currency: "zł",
        discountPercentage: "20%",
      },
      imageUrl: "./assets/product.png",
    },
  ];
  return (
    <div className={styles.root}>
      <div className={styles.details}>
        <h1>LEGO Icons Władca Pierścieni: Rivendell </h1>
        <Button
          text="Є в наявності"
          style={{
            backgroundColor: "rgba(10,184,67,0.1)",
            color: "#0AB843",
            cursor: "inherit",
          }}
        />
      </div>
      <span>
        <Price
          button={false}
          currency={cards[0].price.currency}
          originalPrice={cards[0].price.originalPrice}
          discountPercentage={cards[0].price.discountPercentage}
          discountedPrice={cards[0].price.discountedPrice}
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
