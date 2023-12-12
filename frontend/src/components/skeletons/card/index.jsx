import React from "react";
import ContentLoader from "react-content-loader";

const SkeletonCard = (props) => (
  <ContentLoader
    speed={2}
    width={240}
    height={300}
    viewBox="0 0 240 320"
    backgroundColor="#f3f3f3"
    foregroundColor="#ecebeb"
    {...props}
  >
    <rect x="0" y="0" rx="10" ry="10" width="240" height="300" />
  </ContentLoader>
);

export default SkeletonCard;
