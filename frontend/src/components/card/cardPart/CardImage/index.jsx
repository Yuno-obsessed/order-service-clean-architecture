import styles from './index.module.scss'

export const CardImage = ({ imageUrl, data}) => {
   const isImage = data ? (styles.cardImage) : (styles.cardWithoutImage)
   return (
        data ?
   (<img className={isImage} src={imageUrl}/> )
       :
       (
           <div className={styles.cardImage}>
           <h1 className={styles.cardWithoutImage}>Without Photo</h1>
       </div>)
   )
}
