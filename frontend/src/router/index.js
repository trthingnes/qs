import { createRouter, createWebHistory } from "vue-router"
import HomeView from "../views/HomeView.vue"

const routes = [
    {
        path: "/",
        name: "home",
        component: HomeView,
    },
    {
        path: "/queue",
        name: "queue",
        component: () =>
            import(/* webpackChunkName: "queue" */ "@/views/QueueView.vue"),
    },
    {
        path: "/settings",
        name: "settings",
        component: () =>
            import(
                /* webpackChunkName: "settings" */ "@/views/UserSettingsView.vue"
            ),
    },
    {
        path: "/addtoqueue",
        name: "addtoqueue",
        component: () =>
            import(
                /* webpackChunkName: "settings" */ "@/views/AddToQueueView.vue"
            ),
    },
    {
        path: "/assignments",
        name: "assignments",
        component: () =>
            import(
                /* webpackChunkName: "settings" */ "@/views/AssignmentsView.vue"
            ),
    },
    {
        path: "/assignmentlist",
        name: "assignmentslist",
        component: () =>
            import(
                /* webpackChunkName: "settings" */ "@/views/AssignmentListView.vue"
            ),
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
})

export default router
