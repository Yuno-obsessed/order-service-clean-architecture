import styles from "./index.module.scss";
import { Card } from "../../card/index.jsx";
import { useNavigate } from "react-router-dom";
import SkeletonCard from "../../skeletons/card/index.jsx";

export const CardSelection = () => {
  const cards = []
  const navigate = useNavigate();
  return (
    <div className={styles.root}>
      <h1 className={styles.title}>Найбільший рейтинг 💯</h1>
      <div className={styles.container}>
        <div className={styles.cards}>
          {cards
            ? cards.map((card) => (
                <Card
                  key={card.productId}
                  onClick={() => {
                    console.log(card);
                    return navigate("/product/" + card.productId);
                  }}
                  productId={card.productId}
                  imageUrl={card.imageUrl}
                  price={card.price}
                  productName={card.productName}
                />
              ))
            : Array.from({ length: 4 }, (_, i) => <SkeletonCard key={i} />)}
        </div>
      </div>
    </div>
  );
};
