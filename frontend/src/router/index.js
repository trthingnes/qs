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
        path: "/queue",
        name: "queue",
        component: () =>
            import(/* webpackChunkName: "queue" */ "@/views/QueueView.vue"),
    },
    {
        path: "/queue/enter",
        name: "enter-queue",
        component: () =>
            import(
                /* webpackChunkName: "enter-queue" */ "@/views/EnterQueueView.vue"
            ),
    },
    {
        path: "/assignments",
        name: "assignments",
        component: () =>
            import(
                /* webpackChunkName: "assignments" */ "@/views/AssignmentsView.vue"
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
        path: "/courses/edit",
        name: "edit-course",
        component: () =>
            import(
                /* webpackChinkName: "edit-course" */ "@/views/EditCourseView.vue"
            ),
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
})

export default router
