import AssignmentListView from "@/views/AssignmentListView"
import { shallowMount } from "@vue/test-utils"

describe("AssignmentListView", () => {
    it("Displays subheader", () => {
        const wrapper = shallowMount(AssignmentListView)

        const html = wrapper.html()
        expect(html).toContain("<v-list-subheader")
    })

    // TODO: Test if more than one item is displayed
    it("Displays list items", () => {
        const wrapper = shallowMount(AssignmentListView)

        const html = wrapper.html()
        expect(html).toContain("<v-list-item")
    })

    it("Displays cards", () => {
        const wrapper = shallowMount(AssignmentListView)

        const html = wrapper.html()
        expect(html).toContain("<v-card")
    })
})
