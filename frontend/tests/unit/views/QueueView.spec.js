import QueueView from "@/views/QueueView"
import { shallowMount } from "@vue/test-utils"

describe("QueueView", () => {
    it("Displays subheader", () => {
        const wrapper = shallowMount(QueueView)

        const html = wrapper.html()
        expect(html).toContain("<v-list-subheader")
    })

    it("Displays items", () => {
        const wrapper = shallowMount(QueueView)

        const html = wrapper.html()
        expect(html).toContain("<v-list-item")
    })

    it("Displays item content", () => {
        const wrapper = shallowMount(QueueView)

        const html = wrapper.html()
        expect(html).toContain("<v-list-item-content")
    })
})
