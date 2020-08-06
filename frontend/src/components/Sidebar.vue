<template>
  <div>
    <v-form>
      <v-text-field
        placeholder="search"
        append-icon="mdi-magnify"
      ></v-text-field>
    </v-form>
      <h3>notice</h3>
      <h3>tags</h3>
  </div>
</template>

<script>
export default {
    name: 'sidebar',
}
</script>

<style scoped>
div {
  position: sticky;
  top : 130px;
}
</style>