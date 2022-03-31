import EditSubjectView from "@/views/EditSubjectView"
import { shallowMount } from "@vue/test-utils"

describe("EditSubjectView", () => {
    it("Displays labels", () => {
        const wrapper = shallowMount(EditSubjectView)

        const html = wrapper.html()
        expect(html).toContain("<v-label")
    })

    it("Displays text fields", () => {
        const wrapper = shallowMount(EditSubjectView)

        const html = wrapper.html()
        expect(html).toContain("<v-text-field")
    })

    it("Displays slider", () => {
        const wrapper = shallowMount(EditSubjectView)

        const html = wrapper.html()
        expect(html).toContain("<v-slider")
    })

    // TODO: Find out how to test this
    it("Slider gives right value", () => {
        const wrapper = shallowMount(EditSubjectView)

        const value = wrapper.find('[data-testid="slider"]').setValue(3)
        expect(value).toEqual(3)
    })
})
