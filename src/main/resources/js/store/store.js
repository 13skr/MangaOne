import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        mangaArray: []
    },
    getters: {
        mangaArray: state => {
            return state.mangaArray
        }
    },
    mutations: {
        addMangaMutation(state, manga) {
            state.mangaArray = [
                ...state.mangaArray,
                manga
            ]
        },
        updateMangaMutation(state, manga) {
            const index = state.mangaArray.findIndex(x => x.id === manga.id)

            state.mangaArray = [
                ...state.mangaArray.splice(0, index),
                manga,
                ...state.mangaArray.splice(index + 1)
            ]
        },
        deleteMangaMutation(state, manga) {
            const index = state.mangaArray.findIndex(x => x.id === manga.id)

            if (index > 0) {
                state.mangaArray = [
                    ...state.mangaArray.splice(0, index),
                    ...state.mangaArray.splice(index + 1)
                ]
            }

            if (index === 0) {
                state.mangaArray.length = 0
            }
        },
        addMangaArrayMutation(state, mangaArray) {
            try {
                state.mangaArray = mangaArray
            } catch (e) {
                console.log(e)
            }

        },
    },
    actions: {
        addMangaAction({commit}, manga) {
            axios.post("api/manga", manga)
                .then(response => {
                    commit('addMangaMutation', response.data)
                })
        },
        updateMangaAction({commit}, manga) {
            console.log(manga)
            axios.put("api/manga/" + manga.id)
                .then(response => {
                    commit('updateMangaMutation', response.data)
                })
        },
        deleteMangaAction({commit}, manga) {
            axios.delete("/api/manga/" + manga.id).then(response => {
                commit('deleteMangaMutation', manga)
            })
        },
        addChapterAction({commit}, manga) {
            return new Promise(((resolve) => {
                axios.post('/api/chapter', manga)
                    .then(response => {
                        commit('updateMangaMutation', manga)
                        resolve(response.data)
                    })
            }))
        },
        addPagesAction({commit}, manga) {
            axios.post('/api/chapter/pages', manga.chapters[manga.chapters.length - 1].pages)
                .then(response => {
                    manga.chapters[manga.chapters.length - 1].pages = response.data
                    commit('updateMangaMutation', manga)
                })
        }
    }
})