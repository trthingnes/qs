import CourseSectionComponent from "@/components/navigation/CourseSectionComponent"
import { shallowMount } from "@vue/test-utils"

describe("CourseSelectionComponent", () => {
    it("Displays icon", () => {
        const wrapper = shallowMount(CourseSectionComponent)

        const html = wrapper.html()
        expect(html).toContain("<v-icon")
    })

    it("Displays subheader", () => {
        const wrapper = shallowMount(CourseSectionComponent)

        const html = wrapper.html()
        expect(html).toContain("<v-list-subheader")
    })
})
