describe("renders the home page", () => {
    it("renders",() => {
        cy.visit("/login");
        cy.get("#email").should("exist");
        cy.get("#password").should("exist");
    });

    it("add-artist", () => {
        cy.get("#email")
            .type("thomas@gmail.com")

        cy.get("#password").clear()
            .type("123Thomas")

        cy.get("#login")
            .click()

        cy.get("#artist")
            .click()

        cy.get("#artist-name")
            .type("demoArtist")

        cy.get("#button")
            .click()
    });
})