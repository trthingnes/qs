import AssignmentsView from "@/views/AssignmentsView"
import { shallowMount } from "@vue/test-utils"

describe("AssignmentsView", () => {
    // TODO: Test if multiple components are displayed
    it("Displays components", () => {
        const wrapper = shallowMount(AssignmentsView)

        const html = wrapper.html()
        expect(html).toContain("<v-component")
    })

    it("Displays checkboxes", () => {
        const wrapper = shallowMount(AssignmentsView)

        const html = wrapper.html()
        expect(html).toContain("<v-checkbox")
    })
})
