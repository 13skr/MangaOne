<template>
    <div>
        <h1>MangaOne</h1>
        <MangaList/>
    </div>
</template>

<script>
    import MangaList from "./components/MangaList.vue";

    export default {
        components: {
            MangaList
        }
    }
</script>

<style>
    h1 {
        text-align: center;
        color: white;
    }
</style>
