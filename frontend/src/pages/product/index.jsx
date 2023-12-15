import styles from "./index.module.scss";
import { ProductImage } from "../../components/product/image";
import { Route } from "../../components/route/index.jsx";
import { ProductDetails } from "../../components/product/details";
import { ProductDelivery } from "../../components/product/delivery";
import {useEffect} from "react";
import {useGetProductByIdQuery} from "../../api/product.js";
import {useParams} from "react-router-dom";

export const ProductPage = () => {
    const { cardId } = useParams();

    const {data, isSuccess, error} = useGetProductByIdQuery(cardId)

    useEffect(() => {
        window.scrollTo(0, 0)
    }, [])

  return (
    <div>
      <Route />
        {data && <div className={styles.root}>
            <ProductImage/>
            <div className={styles.detailsContainer}>
                <ProductDetails
                    avalible={data.availability}
                    name={data.name}
                    price={data.price}
                    discount={data.discount}
                />
                <ProductDelivery/>
            </div>
        </div>}
    </div>
  );
};
