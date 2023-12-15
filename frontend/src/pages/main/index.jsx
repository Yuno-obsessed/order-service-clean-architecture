import { Categories } from "../../components/categories/index.jsx";
import { CardSelection } from "../../components/selection/cards/index.jsx";
import { AddCard } from "../../components/selection/addCard";
import styles from "./index.module.scss";
import { Selector } from "../../components/selector";
import { Card } from "../../components/card/index.jsx";
import SkeletonCard from "../../components/skeletons/card";
import {useNavigate} from "react-router-dom";
import {useGetProductsQuery} from "../../api/product.js";
import {useEffect, useState} from "react";

export const MainPage = () => {

  const [cards, setCards] = useState()
  const navigate = useNavigate()

  const { data, error, isLoading, isSuccess, isError } = useGetProductsQuery();
  useEffect(() => {
    if (isSuccess) {
      setCards(data);
    }
  }, [isSuccess, data]);


  return (
    <>
      <Categories />
      <div className={styles.container}>
        <div className={styles.maincontainer}>
          <AddCard />

        </div>
        <Selector />
        <div className={styles.cardcontainer}>
          <div className={styles.root}>
            <div className={styles.container}>
              <h1 className={styles.title}>Весь каталог</h1>
              <div className={styles.cards}>
                {cards?.length !== 0
                  ? cards?.map((card) => (
                      <Card
                        onClick={() => {
                          navigate('/product/' + card.product_id)
                        }}
                        id={card.product_id}
                        key={card.productId}
                        productId={card.product_id}
                        imageUrl={card.imageUrl}
                        price={card.price}
                        discount={card.discount}
                        productName={card.name}
                      />
                    ))
                  : Array.from({ length: 10 }, (_, i) => (
                      <SkeletonCard key={i} />
                    ))}
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
