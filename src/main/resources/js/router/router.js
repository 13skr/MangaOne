import Vue from 'vue'
import VueRouter from "vue-router"
import MangaList from "../components/MangaList.vue"
import Create from "../components/Create.vue"
import Catalog from "../components/Catalog.vue";
import Manga from "../components/Manga.vue"
import Chapter from "../components/Chapter.vue";
import ChapterCreate from "../components/ChapterCreate.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        component: MangaList
    },
    {
        path: '/create',
        component: Create
    },
    {
        path: '/catalog',
        component: Catalog
    },
    {
        path: '/manga/:id',
        component: Manga
    },
    {
        path: '/manga/:id/chapter',
        component: ChapterCreate
    },
    {
        path: '/manga/:id/chapter/:number',
        component: Chapter
    },
    {
        path: '*',
        component: MangaList
    }
]

export default new VueRouter({
    mode: 'history',
    routes
})