<script setup lang="ts">

import Clients from '@/components/Clients.vue'
import Projects from '@/components/Projects.vue'
import { ref } from 'vue'
import Activity from '@/components/Activity.vue'

const currentClientSelected = ref(null)
const currentProjectSelected = ref(null)

const clientSelected = (clientId) => {
  currentClientSelected.value= clientId
  currentProjectSelected.value = null
}

const projectSelected= (project)=>{
  currentProjectSelected.value=project
}
</script>

<template>
  <main class="flex flex-row justify-center items-center">
    <Clients @item-selected="clientSelected($event)" />
    <Projects v-if="currentClientSelected" @project-selected="projectSelected($event)" :currentClientSelected="currentClientSelected" />
    <Activity v-if="currentProjectSelected" :currentProjectSelected="currentProjectSelected" />
  </main>
</template>

<style scoped>

</style>
