import NavigationDrawerComponent from "@/components/NavigationDrawerComponent"
import {mount, shallowMount} from "@vue/test-utils"

describe("NavigationDrawerComponent", () => {
    it("Displays labels", () => {
        const wrapper = shallowMount(NavigationDrawerComponent)

        const html = wrapper.html()
        expect(html).toContain("Dine fag")
        expect(html).toContain("Læringsassistent")
    })

    it("Displays icons", () => {
        const wrapper = shallowMount(NavigationDrawerComponent)

        const html = wrapper.html()
        expect(html).toContain("<v-icon")
    })

    it("Displays list items", () => {
        const wrapper = shallowMount(NavigationDrawerComponent)

        const html = wrapper.html()
        expect(html).toContain("<v-list-item")
    })
})
