<template>
    <tr :class="{ 'bg-green-lighten-3': entry.hasAssistant }">
        <td>{{ entry.username }}</td>
        <td>{{ entry.location }}</td>
        <td>{{ entry.help ? "Hjelp" : "Godkjenning" }}</td>
    </tr>
</template>
<script>
export default {
    props: ["entry"],
    setup() {},
}
</script>
