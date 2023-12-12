import React from "react";
import ContentLoader from "react-content-loader";

const SkeletonText = (props) => (
  <ContentLoader
    speed={2}
    width={476}
    height={124}
    viewBox="0 0 476 124"
    backgroundColor="#f3f3f3"
    foregroundColor="#ecebeb"
    {...props}
  >
    <rect x="0" y="0" rx="10" ry="10" width="250" height="14" />
  </ContentLoader>
);

export default SkeletonText;
