import HeaderComponent from "@/components/HeaderComponent"
import { shallowMount } from "@vue/test-utils"

describe("HeaderComponent", () => {
    it("Displays title", () => {
        const wrapper = shallowMount(HeaderComponent)

        const html = wrapper.html()
        expect(html).toContain("<h4")
    })
})
