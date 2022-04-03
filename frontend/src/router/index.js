import { createRouter, createWebHistory } from "vue-router"

const routes = [
    {
        path: "/",
        name: "home",
        component: () =>
            import(/* webpackChunkName: "home" */ "@/views/HomeView.vue"),
    },
    {
        path: "/settings",
        name: "settings",
        component: () =>
            import(
                /* webpackChunkName: "settings" */ "@/views/SettingsView.vue"
            ),
    },
    {
        path: "/assignments/list",
        name: "list-assignments",
        component: () =>
            import(
                /* webpackChunkName: "assignment-list" */ "@/views/ListAssignmentsView.vue"
            ),
    },
    {
        path: "/courses/add",
        name: "add-course",
        component: () =>
            import(
                /* webpackChunkName: "add-course" */ "@/views/AddCourseView.vue"
            ),
    },
    {
        path: "/courses/edit/:id",
        props: true,
        name: "edit-course",
        component: () =>
            import(
                /* webpackChunkName: "edit-course" */ "@/views/EditCourseView.vue"
            ),
    },
    {
        path: "/courses/view/:id",
        props: true,
        name: "view-course",
        component: () =>
            import(
                /* webpackChunkName: "view-course" */ "@/views/CourseView.vue"
            ),
    },
    {
        path: "/courses/queue/:id",
        props: true,
        name: "view-queue",
        component: () =>
            import(
                /* webpackChunkName: "view-queue" */ "@/views/QueueView.vue"
            ),
    },
    {
        path: "/courses/queue/:id/enter",
        props: true,
        name: "enter-queue",
        component: () =>
            import(
                /* webpackChunkName: "enter-queue" */ "@/views/EnterQueueView.vue"
            ),
    },
    {
        path: "/courses/:id/assignments",
        name: "assignments",
        component: () =>
            import(
                /* webpackChunkName: "assignments" */ "@/views/AssignmentsView.vue"
            ),
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
})

export default router
