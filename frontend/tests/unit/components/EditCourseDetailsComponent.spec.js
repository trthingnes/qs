import EditCourseDetailsComponent from "@/components/course/EditCourseDetailsComponent"
import { mount, shallowMount } from "@vue/test-utils"
import { useCookies } from "vue3-cookies"
import { useRoute } from "vue-router"
import { createApp } from "vue"

describe("EditCourseDetailsComponent", () => {
    it("Displays text-fields", () => {
        const localVue = createApp()
        const wrapper = shallowMount(EditCourseDetailsComponent, {
            localVue,
            useCookies,
            useRoute,
        })

        const html = wrapper.html()
        expect(html).toContain("<v-text-field")
    })

    it("Displays buttons", () => {
        const wrapper = mount(EditCourseDetailsComponent)

        const html = wrapper.html()
        expect(html).toContain("<v-btn")
    })
})
