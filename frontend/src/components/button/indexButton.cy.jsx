import { Button } from "./index.jsx";

describe("Button Component", () => {
  it("renders correctly with default styles", () => {
    cy.mount(<Button text="Click me!" variation="original" />);
    cy.get("button").contains("Click me!").should("be.visible");
    cy.get("button").should("have.css", "background-color", "rgb(10, 184, 67)");
  });

  it("renders correctly with black variation", () => {
    cy.mount(<Button text="Click me!" variation="black" />);
    cy.get("button").should("have.css", "color", "rgb(0, 0, 0)");
    cy.get("button").should("have.css", "border", "1px solid rgb(0, 0, 0)");
  });
});
