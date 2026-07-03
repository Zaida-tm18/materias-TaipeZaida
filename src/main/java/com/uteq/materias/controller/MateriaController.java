package com.uteq.materias.controller;

import com.uteq.materias.model.Materia;
import com.uteq.materias.service.MateriaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/materias")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    // Listado
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("materias", materiaService.listarActivas());
        return "materias/list";
    }

    // Formulario de creacion
    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("materia", new Materia());
        model.addAttribute("esEdicion", false);
        return "materias/form";
    }

    // Procesa la creacion
    @PostMapping
    public String crear(@Valid @ModelAttribute("materia") Materia materia,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (materiaService.codigoDuplicado(materia.getCodigo(), null)) {
            result.rejectValue("codigo", "duplicado", "Ya existe una materia con ese codigo");
        }
        if (result.hasErrors()) {
            model.addAttribute("esEdicion", false);
            return "materias/form";
        }
        materiaService.crear(materia);
        redirectAttributes.addFlashAttribute("mensaje", "Materia creada correctamente.");
        return "redirect:/materias";
    }

    // Formulario de edicion
    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        Materia materia = materiaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada"));
        model.addAttribute("materia", materia);
        model.addAttribute("esEdicion", true);
        return "materias/form";
    }

    // Procesa la edicion
    @PostMapping("/{id}")
    public String actualizar(@PathVariable Long id,
                              @Valid @ModelAttribute("materia") Materia materia,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (materiaService.codigoDuplicado(materia.getCodigo(), id)) {
            result.rejectValue("codigo", "duplicado", "Ya existe otra materia con ese codigo");
        }
        if (result.hasErrors()) {
            model.addAttribute("esEdicion", true);
            return "materias/form";
        }
        materiaService.actualizar(id, materia);
        redirectAttributes.addFlashAttribute("mensaje", "Materia actualizada correctamente.");
        return "redirect:/materias";
    }

    // Eliminacion logica
    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        materiaService.eliminarLogicamente(id);
        redirectAttributes.addFlashAttribute("mensaje", "Materia eliminada (baja logica).");
        return "redirect:/materias";
    }
}
