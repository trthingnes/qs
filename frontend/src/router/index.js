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
                /* webpackChunkName: "settings" */ "@/views/SettingsView.vue"
            ),
    },
    {
        path: "/addtoqueue",
        name: "addtoqueue",
        component: () =>
            import(/* webpackChunkName: "add" */ "@/views/AddToQueueView.vue"),
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
        name: "assignmentlist",
        component: () =>
            import(
                /* webpackChunkName: "assignmentlist" */ "@/views/AssignmentListView.vue"
            ),
    },
    {
        path: "/courses/add",
        name: "addsubject",
        component: () =>
            import(
                /* webpackChunkName: "addsubject" */ "@/views/AddSubjectView.vue"
            ),
    },
    {
        path: "/courses/edit",
        name: "editsubject",
        component: () =>
            import(
                /* webpackChinkName: "editsubject" */ "@/views/EditSubjectView.vue"
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
