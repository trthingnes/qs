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

    it("Displays correct title", () => {
        const title = "Test title"
        const wrapper = shallowMount(CourseSectionComponent, {
            props: {
                title: title,
            },
        })

        const subheader = wrapper.find("[data-testid='subheader']")
        expect(subheader.html()).toContain(title)
    })

    it("Displays correct item info", () => {
        const id = "TEST1001"
        const name = "Testing basics"

        const wrapper = shallowMount(CourseSectionComponent, {
            props: {
                courses: [
                    {
                        id: id,
                        name: name,
                    },
                ],
            },
        })

        const item = wrapper.find("[data-testid='item']")
        expect(item.html()).toContain(id)
        expect(item.html()).toContain(name)
    })
})
