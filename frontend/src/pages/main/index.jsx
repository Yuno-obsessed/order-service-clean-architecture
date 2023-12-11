import { Categories } from "../../components/categories/index.jsx";
import { CardSelection } from "../../components/selection/cards/index.jsx";
import { AddCard } from "../../components/selection/addCard";
import styles from "./index.module.scss";
import { Selector } from "../../components/selector";
import { Card } from "../../components/card/index.jsx";
import {UNSAFE_LocationContext} from "react-router-dom";
import SkeletonCard from "../../components/skeletons/card";

export const MainPage = () => {
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
    {
      productId: "31109",
      productName: "LEGO Icons Władca Pierścieni: Rivendell",
      price: {
        discountedPrice: "1849.00",
        currency: "zł",
      },
      imageUrl: "./assets/product.png",
    },
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
    {
      productId: "31109",
      productName: "LEGO Icons Władca Pierścieni: Rivendell",
      price: {
        originalPrice: "2089.00",
        discountedPrice: "1849.00",
        currency: "zł",
        discountPercentage: "20%",
      },
      imageUrl: "/assets/product.png",
    },
    {
      productId: "31109",
      productName: "LEGO Icons Władca Pierścieni: Rivendell",
      price: {
        originalPrice: "2089.00",
        discountedPrice: "1849.00",
        currency: "zł",
        discountPercentage: "20%",
      },
      imageUrl: "/assets/product.png",
    },
    {
      productId: "31109",
      productName: "LEGO Icons Władca Pierścieni: Rivendell",
      price: {
        originalPrice: "2089.00",
        discountedPrice: "1849.00",
        currency: "zł",
        discountPercentage: "20%",
      },
      imageUrl: "/assets/product.png",
    },
    {
      productId: "31109",
      productName: "LEGO Icons Władca Pierścieni: Rivendell",
      price: {
        originalPrice: "2089.00",
        discountedPrice: "1849.00",
        currency: "zł",
        discountPercentage: "20%",
      },
      imageUrl: "/assets/product.png",
    },
    {
      productId: "31109",
      productName: "LEGO Icons Władca Pierścieni: Rivendell",
      price: {
        originalPrice: "2089.00",
        discountedPrice: "1849.00",
        currency: "zł",
        discountPercentage: "20%",
      },
      imageUrl: "/assets/product.png",
    },
    {
      productId: "31109",
      productName: "LEGO Icons Władca Pierścieni: Rivendell",
      price: {
        originalPrice: "2089.00",
        discountedPrice: "1849.00",
        currency: "zł",
        discountPercentage: "20%",
      },
      imageUrl: "/assets/product.png",
    },
    {
      productId: "31109",
      productName: "LEGO Icons Władca Pierścieni: Rivendell",
      price: {
        originalPrice: "2089.00",
        discountedPrice: "1849.00",
        currency: "zł",
        discountPercentage: "20%",
      },
      imageUrl: "/assets/product.png",
    },
  ];

  return (
    <>
      <Categories />
      <div className={styles.container}>
        <div className={styles.maincontainer}>
          <AddCard />
          <CardSelection />
        </div>
        <Selector />
        <div className={styles.cardcontainer}>
          <div className={styles.root}>
            <div className={styles.container}>
              <h1 className={styles.title}>Весь каталог</h1>
              <div className={styles.cards}>
                {!cards ? (
                    cards.map((card) => (
                        <Card
                            key={card.productId}
                            productId={card.productId}
                            imageUrl={card.imageUrl}
                            price={card.price}
                            productName={card.productName}
                        />
                    ))
                ) : (
                    Array.from({ length: 10 }, (_, i) => (
                        <SkeletonCard key={i} />
                    ))
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
