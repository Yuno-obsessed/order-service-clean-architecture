describe("template spec", () => {
  const testHeader = (viewport) => {
    if (viewport) {
      cy.viewport(viewport);
    }
    checkUrl('login', 'header_user');
    checkUrl('wishlist', 'header_heart');
    checkUrl('cart', 'header_cart');
    checkUrl('', 'header_title');
  };

  const checkUrl = (urlSuffix, dataTest ) => {
    if(dataTest){
      cy.get(`[data-test=${dataTest}]`).click();
    }
    cy.url().should('eq', `http://localhost:5173/${urlSuffix}`);
  };

  beforeEach(() => {
    cy.visit('http://localhost:5173/');
  });

  it("Correct login", () => {
    cy.visit("http://localhost:5173/login");
    cy.get('[name=email]').type('example@gmail.com');
    cy.get('[name=password]').type('password');
    cy.get('form').submit();
    checkUrl('', '');
  });

  it("Incorrect login", () => {
    cy.visit("http://localhost:5173/login");
    cy.get('[name=email]').type('exampledontright@gmail.com');
    cy.get('[name=password]').type('password');
    cy.get('form').submit();
    checkUrl('login', '');
  });

  it("Header test", () => {
    testHeader();
  });

  it("Header test mobile version", () => {
    testHeader('iphone-6');
  });
});
