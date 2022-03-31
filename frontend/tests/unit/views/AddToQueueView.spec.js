import AddToQueueView from "@/views/AddToQueueView"
import { shallowMount } from "@vue/test-utils"

describe("AddToQueueView", () => {
    it("Displays text fields", () => {
        const wrapper = shallowMount(AddToQueueView)

        const html = wrapper.html()
        expect(html).toContain("<v-text-field")
    })

    it("Displays checkboxes", () => {
        const wrapper = shallowMount(AddToQueueView)

        const html = wrapper.html()
        expect(html).toContain("<v-checkbox")
    })

    it("Displays buttons", () => {
        const wrapper = shallowMount(AddToQueueView)

        const html = wrapper.html()
        expect(html).toContain("<v-btn")
    })

    it("Displays switches", () => {
        const wrapper = shallowMount(AddToQueueView)

        const html = wrapper.html()
        expect(html).toContain("<v-switch")
    })
})
