import AddCourseParticipantComponent from "@/components/course/AddCourseParticipantComponent"
import { shallowMount } from "@vue/test-utils"

describe("AddCourseParticipantComponent", () => {
    it("Displays student header correctly", () => {
        const wrapper = shallowMount(AddCourseParticipantComponent)

        const html = wrapper.html()
        expect(html).toContain("Legg til en student")
    })

    it("Displays assistant header correctly", () => {
        const wrapper = shallowMount(AddCourseParticipantComponent, {
            props: {
                assistant: true,
            },
        })

        const html = wrapper.html()
        expect(html).toContain("Legg til en assistent")
    })

    it("Displays student button correctly", () => {
        const wrapper = shallowMount(AddCourseParticipantComponent)

        const button = wrapper.find("[data-testid='button']")
        expect(button.html()).toContain("student")
    })

    it("Displays assistant header correctly", () => {
        const wrapper = shallowMount(AddCourseParticipantComponent, {
            props: {
                assistant: true,
            },
        })

        const button = wrapper.find("[data-testid='button']")
        expect(button.html()).toContain("assistent")
    })
})
