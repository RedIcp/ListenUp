describe("renders the home page", () => {
  it("renders",() => {
    cy.visit("http://localhost:3000/login");
    cy.get("#email").should("exist");
    cy.get("#password").should("exist");
  });

  it("login-invalid-credential", () => {
    cy.get("#email")
        .type("thoomas@gmail.com")

    cy.get("#password")
        .type("123Thomas")

    cy.get("#login")
        .click()

    cy.get("#errmsg").should("contain", "Invalid Credential")
        .and("be.visible")
  });

  it("login-valid", () => {
    cy.get("#email").clear()
    cy.get("#password").clear()
    cy.get("#errmsg").should("be.hidden")

    cy.get("#email")
        .type("thomas@gmail.com")

    cy.get("#password").clear()
        .type("123Thomas")

    cy.get("#login")
        .click()
  });
})