describe("renders the home page", () => {
    it("renders",() => {
        cy.visit("http://localhost:3000/register");
        cy.get("#email").should("exist");
        cy.get("#username").should("exist");
        cy.get("#password").should("exist");
    });
    it("register-valid", () => {
        cy.get("#username")
            .type("keyboard")
        cy.get("#email")
            .type("keyboard@gmail.com")
        cy.get("#password")
            .type("123Keyboard@")
        cy.get("#register")
            .click()
    });
    it("register-invalid", () => {
        cy.get("#username").clear()
        cy.get("#email").clear()
        cy.get("#password").clear()

        cy.get("#username")
            .type("Thomas")
        cy.get("#email")
            .type("thomas@gmail.com")
        cy.get("#password")
            .type("123Thomas@")
        cy.get("#register")
            .click()

        cy.get("#errmsg").should("contain", "Username or email Taken")
            .and("be.visible")
    });
})