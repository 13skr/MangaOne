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
            console.log('started deleting manga')
            const index = state.mangaArray.findIndex(x => x.id === manga.id)
            console.log('get index of this manga', index)

            if (index > 0) {
                console.log('inside 1 if')
                state.mangaArray = [
                    ...state.mangaArray.splice(0, index),
                    ...state.mangaArray.splice(index + 1)
                ]
                console.log('finished deleting in 1 if', state.mangaArray)
            }

            console.log('between 2 if')

            if (index === 0) {
                console.log('inside 2 if')
                state.mangaArray.length = 0
                console.log('finished deleting in 2 if', state.mangaArray)
            }

            console.log('deleted')
        },
        addMangaArrayMutation(state, mangaArray) {
            state.mangaArray = mangaArray
        },
        findManga(state, index) {
            return state.mangaArray.find(x => x.id === index)

            // let result
            //
            // for (let i = 0; i < state.mangaArray.length; i++) {
            //     if (state.mangaArray[i].id === index) {
            //         result = state.mangaArray[i]
            //         break
            //     }
            // }
            //
            // return result

            // return state.mangaArray.forEach(x => {
            //     if (x.id === index) {
            //         return x
            //     }
            // })
        }
    },
    actions: {
        addMangaAction({commit}, manga) {
            axios.post("api/manga", manga)
                .then(response => commit('addMangaMutation', response.data))
        },
        updateMangaAction({commit}, manga) {
            console.log(manga)
            axios.put("api/manga/" + manga.id)
                .then(response => {
                    console.log(response.data)
                    commit('updateMangaMutation', response.data)
                })
        },
        deleteMangaAction({commit}, manga) {
            axios.delete("/api/manga/" + manga.id).then(response => {
                console.log(response.data)
                commit('deleteMangaMutation', manga)
            })
        }
    }
})