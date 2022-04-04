import EditCourseParticipantsComponent from "@/components/course/EditCourseParticipantsComponent"
import { shallowMount } from "@vue/test-utils"

describe("EditCourseParticipantsComponent", () => {
    it("Displays header", () => {
        const wrapper = shallowMount(EditCourseParticipantsComponent)

        const html = wrapper.html()
        expect(html).toContain("<HeaderComponent")
    })
})
