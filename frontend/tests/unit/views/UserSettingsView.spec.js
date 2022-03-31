import UserSettingsView from "@/views/UserSettingsView"
import { shallowMount } from "@vue/test-utils"

describe("UserSettingsView", () => {
    it("emits api with correct payload", () => {
        const wrapper = shallowMount(UserSettingsView)
        const credentials = {
            firstname: "Ola",
            lastname: "Normann",
            email: "test@test.no",
            password: "root",
        }

        wrapper
            .find('[data-testid="firstname"]')
            .setValue(credentials.firstname)
        wrapper.find('[data-testid="lastname"]').setValue(credentials.lastname)
        wrapper.find('[data-testid="email"]').setValue(credentials.email)
        wrapper.find('[data-testid="password"]').setValue(credentials.password)
        wrapper.trigger("submit")

        const calls = wrapper.trigger("saveUserInfo")
        expect(calls).toHaveLength(1)
        expect(calls[0][0]).toMatchObject(credentials)
    })

    it("Displays text fields", () => {
        const wrapper = shallowMount(UserSettingsView)

        const html = wrapper.html()
        expect(html).toContain("<v-text-field")
    })

    it("Displays submit button", () => {
        const wrapper = shallowMount(UserSettingsView)

        const html = wrapper.html()
        expect(html).toContain("<v-btn")
    })
})
